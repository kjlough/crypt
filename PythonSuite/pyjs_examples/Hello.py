import pyjd # this is dummy in pyjs.
from pyjamas.ui.RootPanel import RootPanel
from pyjamas.ui.Button import Button
from pyjamas.ui.HTML import HTML
from pyjamas.ui.TextBox import TextBox
from pyjamas import Window

import pygwt

# Components for the homepage:
exampleHTML = """
<head>
    <title> Crypto App </title>
</head>
<body>
    <h1>This is a header!</h1>
    
    <p>The urge to discover secrets is deeply ingrained in human nature; even the least curious mind is roused by the promise of sharing knowledge withheld from others. Some are fortunage enough to find a job which consists in the solution of mysteries, but most of  us are driven to sublimate this urge by the solving of artificial puzzles devised for our entertainment. Detective stories or crossword puzzles cater for the majority; the solution of secret codes may be the pursuit of a few.</p>
    
    <a href=https://en.wikipedia.org/wiki/Linear_B >John Chadwick (The Decipherment of Linear B) </a>
<body>
"""
text = TextBox()


def onClick(clicked_component):
    clicked_component.setText("You clicked \"This\"!")
    text.setText("You clicked a button!")
    Window.alert("This is a Window.alert()")


class Homepage:
    def onModuleLoad(self):
        b = Button("Click me", onClick)
        h = HTML(exampleHTML)
        RootPanel().add(text)
        RootPanel().add(b)
        RootPanel().add(h)


if __name__ == '__main__':
    pyjd.setup("./public/Hello.html")
    app = Homepage
    app.onModuleLoad()
    pyjd.run()
