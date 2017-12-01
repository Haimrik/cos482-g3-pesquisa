#!/usr/bin/env python
import re
import base64
import tempfile
from subprocess import Popen, PIPE
from zlib import adler32
import logging
import markdown
from markdown.util import etree, AtomicString

class BaseCompiler:
    def toBase64(self, code, imgformat):
        base64str = {
            'png': 'png',
            'svg': 'svg+xml'
        }
        imgformat = self.check_format(imgformat)
        return 'data:image/' + base64str[imgformat] + ';base64,{0}'.format(
            base64.b64encode(self.generate_image(code, imgformat)).decode('ascii')
        )

    def check_format(self, imgformat):
        if not imgformat in [ "png", "svg" ]:
            logger.error("Bad output image format '"+imgformat+"', using png")
            return "png"
        return imgformat

    def generate_image(self, code, imgformat):
        code = code.encode('utf8')
        cmdline = self.command(code, imgformat)

        try:
            p = Popen(cmdline, stdin=PIPE, stdout=PIPE, stderr=PIPE)
            out, err = p.communicate(input=code)
        except Exception as exc:
            raise Exception('Failed to run ' + delimiter + ': %s' % exc)
        else:
            if p.returncode == 0:
                return out
            else:
                raise RuntimeError('Error in "' + delimiter + '" directive: %s' % err)

    def command(self, code, imgformat):
        pass


class DotCompiler(BaseCompiler):
    def command(self, code, imgformat):
        return ["dot", "-T"+imgformat]

class PlantUMLCompiler(BaseCompiler):
    def command(self, code, imgformat):
        return ["plantuml", "-p", "-t"+imgformat]

class DitaaCompiler(BaseCompiler):
    def command(self, code, imgformat):
        return ["ditaa", "-"]

# For details see https://pythonhosted.org/Markdown/extensions/api.html#blockparser
class TextimagerBlockProcessor(markdown.blockprocessors.BlockProcessor):
    def __init__(self, *args, **kwargs):
        self.compilers = {
            'plantuml': PlantUMLCompiler(),
            'dot': DotCompiler(),
            'ditaa': DitaaCompiler()
        }

        super(TextimagerBlockProcessor, self).__init__(*args, **kwargs)

    # Regular expression inspired by the codehilite Markdown plugin
    RE = re.compile(r'''\s*::(?P<delimiter>(dot|plantuml|ditaa))::
                        \s*(format=(?P<quot>"|')(?P<format>\w+)(?P=quot))?
                        \s*(classes=(?P<quot1>"|')(?P<classes>[\w\s]+)(?P=quot1))?
                        \s*(alt=(?P<quot2>"|')(?P<alt>[^"']+)(?P=quot2))?
                        \s*(title=(?P<quot3>"|')(?P<title>[^"']+)(?P=quot3))?
                    ''', re.VERBOSE+re.UNICODE)
    # Regular expression for identify end of dot script
    RE_END = re.compile(r'.*::end-::')

    def test(self, parent, block):
        return self.RE.search(block)

    def run(self, parent, blocks):
        block = blocks.pop(0)
        text = block

        # Parse configuration params
        m = self.RE.search(block)
        delimiter = m.group('delimiter')
        imgformat = m.group('format') if m.group('format') else self.config['format']
        classes = m.group('classes') if m.group('classes') else self.config['classes']
        if self.config['include_delimiter_class'] == "true":
            classes += " delimiter"
        alt = m.group('alt') if m.group('alt') else self.config['alt']
        title = m.group('title') if m.group('title') else self.config['title']

        # Read blocks until end marker found
        end_re = re.compile(r'::end-' + delimiter + "::")

        while blocks and not end_re.search(block):
            block = blocks.pop(0)
            text += '\n' + block
        else:
            if not blocks:
                raise RuntimeError("Imager block not closed")

        # Remove block header and footer
        text = re.sub(self.RE, "", re.sub(end_re, "", text))
        text = "\n".join(text.split('\n'))

        p = etree.SubElement(parent, 'p')
        # Firefox handles only base64 encoded SVGs
        data = self.compilers[delimiter].toBase64(text, imgformat)
        img = etree.SubElement(p, 'img')
        img.attrib['src'    ] = data
        img.attrib['classes'] = classes
        img.attrib['alt'    ] = alt
        img.attrib['title'  ] = title


# For details see https://pythonhosted.org/Markdown/extensions/api.html#extendmarkdown
class TextimagerMarkdownExtension(markdown.Extension):
    # For details see https://pythonhosted.org/Markdown/extensions/api.html#configsettings
    def __init__(self, *args, **kwargs):
        self.config = {
            'classes': ["", "Space separated list of classes for the generated image."],
            'alt': ["", "Text to show when image is not available."],
            'format': ["png", "Format of image to generate (png, svg or txt). Defaults to 'png'."],
            'title': ["", "Tooltip for the diagram"],
            'include_delimiter_class': ["true", "Whether or not to include the delimiter name as a class"]
        }

        super(TextimagerMarkdownExtension, self).__init__(*args, **kwargs)

    def extendMarkdown(self, md, md_globals):
        blockprocessor = TextimagerBlockProcessor(md.parser)
        blockprocessor.config = self.getConfigs()
        md.parser.blockprocessors.add('textimager', blockprocessor, '>code')


def makeExtension(*args, **kwargs):
    return TextimagerMarkdownExtension(*args, **kwargs)
