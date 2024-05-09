from tkinter import *
from tkinter.colorchooser import askcolor
from tkinter.messagebox import *


class DrawMoveRubberShapes(object):
    def __init__(self):
        window = Tk()  # Create a window
        window.title("Lab 09: Draw Rubber Shapes, Select and Move")
        window.resizable(False, False)

        # create pulldown menu for choosing a shape
        menubar = Menu(window)
        window.config(menu=menubar)  # Display the menu bar

        # create a pulldown menu, and add it to the menu bar
        shapeMenu = Menu(menubar,
                         tearoff=True)
        shapeMenu.add_radiobutton(label="line",
                                  command=self.chooseLine)  #to provide a menu that allows the user to create lines
        shapeMenu.add_radiobutton(label="oval/circle",
                                  command=self.chooseOvalCircle) #to provide a menu that allows the user to create oval or circle shapes
        shapeMenu.add_radiobutton(label="rectangle",
                                  command=self.chooseRectangle)  #to provide a menu that allows the user to create rectangle shapes
        menubar.add_cascade(label="Choose a Shape",
                            menu=shapeMenu)  #to provide a menu that allows the user to select shapes, i.e. lines, ovals or circles, and rectangles

        # Create a canvas, bound to mouse events
        self.canvas = Canvas(window,
                             width=500, height=400,
                             relief='ridge',
                             bg='white',
                             bd=5)
        self.textId = self.canvas.create_text(5, 5,
                                              text="Press h for help",
                                              font=("Courier", 10),
                                              anchor=NW)
        self.canvas.pack()
        self.canvas.bind('<ButtonPress-1>',
                         self.onStart)  # click
        self.canvas.bind('<B1-Motion>',
                         self.onGrow)  # and drag
        self.canvas.bind('<ButtonPress-3>',
                         self.onSelect)  # right click
        self.canvas.bind('<B3-Motion>',
                         self.onMove)
        self.canvas.bind('<KeyPress-h>',  # h press
                         self.onHelp)
        self.canvas.bind('<KeyPress-d>', # to delete the selected shape 
                         self.onDelete)
        self.canvas.focus_set()

        # for remembering the last drawing and shape
        self.drawn = None
        self.shape = self.canvas.create_rectangle

        # Create and add a frame to window
        frame1 = Frame(window,
                       borderwidth=2)
        frame1.pack()

        # Create a button for choosing color using a color chooser
        self.fillColor = StringVar()
        self.fillColor.set('red')

        def colorCommand(): # create colorCommand function
            (rgb, color) = askcolor()
            if color is not None:
                self.fillColor.set(color)
                colorButton["bg"] = color

        colorButton = Button(frame1,
                             text="Color",
                             command=colorCommand,
                             bg=self.fillColor.get())
        colorButton.grid(row=1, column=1)

        def clearCommand(): # create clearCommand function
            self.canvas.delete("all")
            self.textId = self.canvas.create_text(5, 5,
                                                  text="Press h for help",
                                                  font=("Courier", 10),
                                                  anchor=NW)

        clearButton = Button(frame1,
                             text="Clear",
                             command=clearCommand)
        clearButton.grid(row=1, column=2)

        window.mainloop()

    def chooseLine(self): # create chooseLine function
        self.shape = self.canvas.create_line # to create lines

    def chooseOvalCircle(self):  # create chooseOvalCircle function
        self.shape = self.canvas.create_oval # to create ovals or circles

    def chooseRectangle(self): # create chooseRectangle function
        self.shape = self.canvas.create_rectangle # to create rectangles 

    # remember the left mouse press to start drawing
    def onStart(self, event): # create onStart function
        self.start = event
        self.drawn = None

    # elastic drawing: delete and redraw, repeatedly
    def onGrow(self, event):  # create onGrow function
        canvas = event.widget
        if self.drawn:
            canvas.delete(self.drawn)

        objectId = self.shape(self.start.x, self.start.y,
                              event.x, event.y,
                              fill = self.fillColor.get(),
                              width = 0)
        self.drawn = objectId

    def onSelect(self, event):  # create onSelect function
        self.start = event

        canvas = event.widget
        self.closestObject = canvas.find_closest(event.x, event.y)

    # move the shape to the click spot
    def onMove(self, event):  # create onMove function
        canvas = event.widget
        if (self.closestObject and self.closestObject != (self.textId,)):
            diffX, diffY = (event.x-self.start.x), (event.y - self.start.y)
            canvas.move(self.closestObject,
                        diffX, diffY)
        self.start = event

    def onHelp(self, event):  # create onHelp function
        showinfo("Draw, Select, Move",
                 "Mouse commands:\
                 \n Left+Drag = Draw new rubber shape\
                 \n Right = Select a shape\
                 \n Right+Drag = Drag the selected shape\
                 \n\
                 \nKeyboard commands:\
                 \n d = Delete the selected shape\
                 \n h = Help")

    def onDelete(self, event):  # create onDelete function
        canvas = event.widget
        if (self.closestObject and self.closestObject != (self.textId,)):
            canvas.delete(self.closestObject)


if __name__ == '__main__':
    DrawMoveRubberShapes()
