from tkinter import *
from tkinter import messagebox
class Barcode(object):
    def __init__(self, root): # create __init__ function
        # create main window
        root.title('EAN-13 [by Angelie Shania Reva]')
        # create the first label that contains instructions for filling in/entry
        self.label = Label(root, text = 'Save barcode to PS file [eg: EAN13.eps]:', font = "Arial 15 bold")
        self.label.pack()
        # create an entry or a place to get input from the user
        self.entry = Entry(root, font = "Arial 15")
        self.entry.pack()
        self.entry.bind("<Return>", self.validating_code)
        # create the second label that contains instructions for filling in/entry
        self.label1 = Label(root, text = 'Enter code (first 12 decimal digits):', font = "Arial 15 bold")
        self.label1.pack()
        # create the second entry to get input from user
        self.entry1 = Entry(root, font = "Arial 15")
        self.entry1.pack()
        self.entry1.bind("<Return>", self.validating_code)
        # create a canvas on which to visualize the output
        self.canva = Canvas(root, width = 400, height = 400, bg = 'white')
        self.canva.pack()
        # move the cursor to the first entry
        self.entry.focus()
        
        root.mainloop()

    # create a function to check the code given in the second entry
    def validating_code(self, event): # create validating_code function
        self.code = self.entry1.get() #take the value from the variable self.entry1
        if len(self.code) == 12 :
            try :
                self.check_File(event)
            except KeyError:
                messagebox.showerror("CODE ERROR!", "The entered code are not integers.\nPlease try again.")
            except ValueError:
                messagebox.showerror("CODE ERROR!", "The entered code are not integers.\nPlease try again.")
        else :
            messagebox.showerror("Wrong input!", "Please enter correct input code.")
            self.entry1.focus()

    # create a function to check the input in the first entry or given file name
    def check_File(self, event) : # create check_File function
        if self.entry.get()[-4:] != ".eps" :
            messagebox.showerror("ERROR!", "File name is invalid!")
            self.entry.focus()
        else :
            self.process()

   # create a method to process the given input
    def process(self): # create process method
        # delete the entire canvas before inserting it
        self.canva.delete('all')
        # make a caption/writing at the top of the canvas
        self.canva.create_text(205, 30, text = "EAN-13 Barcode:", font = 'Arial 15 bold')
        # create a dictionary to translate barcodes
        Code_L = {'0':'0001101', '1':'0011001', '2':'0010011', '3':'0111101', '4':'0100011', '5':'0110001', '6':'0101111', '7':'0111011', '8':'0110111', '9':'0001011'}
        Code_R = {'0':'1110010', '1':'1100110', '2':'1101100', '3':'1000010', '4':'1011100', '5':'1001110', '6':'1010000', '7':'1000100', '8':'1001000', '9':'1110100'}
        Code_G = {'0':'0100111', '1':'0110011', '2':'0011011', '3':'0100001', '4':'0011101', '5':'0111001', '6':'0000101', '7':'0010001', '8':'0001001', '9':'0010111'}
        # translate the first digit to translate the other digits
        First_Digit = {'0':'LLLLLL', '1':'LLGLGG', '2':'LLGGLG', '3':'LLGGGL', '4':'LGLLGG', '5':'LGGLLG', '6':'LGGGLL', '7':'LGLGLG', '8':'LGLGGL', '9':'LGGLGL'}
        # create the variables needed for the next step
        self.name_1 = self.entry.get()
        self.code_1 = self.entry1.get()
        digit_check = self.Check_digit()
        self.digit = self.code_1 + digit_check
        First = self.digit[0]
        Digit_1 = self.digit[1:7]
        Digit_2 = self.digit[7:]
        New_code = First_Digit[First] 

        x, x2, y, y1, y2 = 100, 90, 50, 175, 185
        # process of creating barcode images
        # creating the first barrier
        for barrier_first in range(1, 4):
            if barrier_first % 2 == 1:
                self.canva.create_line(x, y, x, y2, width = 2, fill = 'blue')               
            else:
                self.canva.create_line(x, y, x, y2, width = 2, fill = 'white')
            x += 2
        # making the first 6 digit part of the number
        for first_part in range(0,6):
            if New_code[first_part] == 'L':
                code_1 = Code_L[Digit_1[first_part]]
                for j in code_1 :
                    if j == '1':
                        self.canva.create_line(x, y, x, y1, width = 2, fill = 'green')
                    else :
                        self.canva.create_line(x, y, x, y1, width = 2, fill = 'white')
                    x += 2
            else:
                code_1 = Code_G[Digit_1[first_part]]
                for j in code_1 :
                    if j == '1':
                        self.canva.create_line(x, y, x, y1, width = 2, fill = 'green')
                    else :
                        self.canva.create_line(x, y, x, y1, width = 2, fill = 'white')
                    x +=2
        # creating the second barrier           
        for barrier_second in range (1, 6):
             if barrier_second % 2 == 0:
                self.canva.create_line(x, y, x, y2, width = 2, fill = 'blue')
                x += 2
             else:
                self.canva.create_line(x, y, x, y2, width = 2, fill = 'white')
                x += 2
        # making the last 6 digit part       
        for second_part in range(0, 6):
             code_2 = Code_R[Digit_2[second_part]]
             for k in code_2 :
                    if k == '1':
                        self.canva.create_line(x, y, x, y1, width = 2, fill = 'green')
                    else :
                        self.canva.create_line(x, y, x, y1, width = 2, fill = 'white')
                    x += 2
        # creating the third barrier
        for barrier_third in range (1, 4):
             if barrier_third % 2 == 1:
                self.canva.create_line(x, y, x, y2, width = 2, fill = 'blue')
             else:
                self.canva.create_line(x, y, x, y2, width = 2, fill = 'white')
             x += 2          
        # create text containing numbers from user input on canvas
        self.canva.create_text(x2, 200, text = First, font = "Arial 15 bold", fill = "black")

        for character in Digit_1:
             self.canva.create_text(x2 + 20, 200, text = character, font = "Arial 15 bold", fill = "black")
             x2 += 15
        for character in Digit_2:
             self.canva.create_text(x2 + 25, 200, text = character, font = "Arial 15 bold", fill = "black")
             x2 += 15

        self.canva.create_text(195, 230, text = f'Check Digit: {self.Check_digit()}', font = 'Arial 18 bold', fill = "orange")
        
        self.SaveFile()
    # perform calculations for the last digit
    def Check_digit(self): # create Check_digit function
        all_digit = self.entry1.get()
        odd = []
        even = []
        for i in range(0, 12):
            if i % 2 == 1:
                even.append(int(all_digit[i]))
            elif i % 2 == 0:
                odd.append(int(all_digit[i]))
                
        check_sum = (sum(even) * 3) + (sum(odd))
        Num_last = check_sum % 10
        if (Num_last != 0):
            check_digit = 10 - Num_last
        else :
            check_digit = Num_last
        return str(check_digit)
        
    # save the file
    def SaveFile(self):
        filename = self.entry.get()[:-3] + self.entry.get()[-3:].lower()
        self.canva.postscript(file = filename, colormode = 'color')

# run the class
if __name__=="__main__":
    root = Tk()
    Barcode(root)
        
 
             
             
             
             
             
                    
                        

        

        
        




















        
