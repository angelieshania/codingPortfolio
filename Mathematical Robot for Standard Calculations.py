# Import library time dan random
from time import sleep
from random import randint

# Make a menu list
list_main_menu = ['Addition', 'Subtraction', 'Mix', 'End Program']
list_mix_menu = ['Free Quiz', 'Quiz 5', 'Change Mode', 'End Program']

print('Hello, welcome to Mathbot')

# Enter infinite looping to display the main menu continuously until the program is terminated
while True:
    main_menu = True
    select_menu = 0

    # Show main menu
    if main_menu:
        print('Select Mode:')
        # Print main menu
        for number, menu in enumerate(list_main_menu):
            print(str(number + 1) + '.', menu)

        print('')

        # Make error handling if the menu does not meet the requirements
        try:
            select_menu = int(input('Enter command: '))
            if select_menu > 4:
                raise ValueError

        except ValueError:
            print('There seems to be a mistake in the input')
            sleep(1)

        # Selecting the first menu / menu 1
        if select_menu == 1:
            """
            MENU ADDITION
            All questions given are only about addition
            """
            menu_addition = True

            print("You've selected addition mode, please select the desired quiz mode")

            while menu_addition:
                print('Select quiz:')
                score = 0

                for number, mix_menu in enumerate(list_mix_menu):
                    print(str(number + 1) + '.', mix_menu)

                # Make error handling if the menu does not meet the requirements
                try:
                    select_menu = int(input('Enter quiz mode: '))

                    if select_menu > 4:
                        raise ValueError

                except ValueError:
                    print('There seems to be a mistake in the input')
                    sleep(1)

                # Select sub menu 1
                if select_menu == 1:
                    """
                    FREE QUIZ MENU ADDITION
                    """

                    enter_quiz = True

                    while enter_quiz:
                        # Random numbers between 1 - 10
                        number1 = randint(1, 10)
                        number2 = randint(1, 10)

                        print(f'What is {number1} + {number2}?')
                        answer = input('Answer: ')

                        # Make error handling if the menu does not meet the requirements
                        try:
                            if answer.strip() == 'end quiz':
                                enter_quiz = False
                            elif int(answer) != (number1 + number2):
                                print('Sorry, that is incorrect. The answer is', number1 + number2)
                                sleep(1)
                            elif int(answer) == (number1 + number2):
                                print('Yay, correct!')
                                sleep(1)
                            else:
                                raise ValueError
                        except ValueError:
                            print('There seems to be a mistake in the input')
                            sleep(1)

                        print('')

                # Select sub menu 2
                elif select_menu == 2:
                    """
                    QUIZ 5 MENU ADDITION
                    """
                    # Make questions 5 times
                    for question in range(5):
                        # Search random number between 1 and 10
                        number1 = randint(1, 10)
                        number2 = randint(1, 10)

                        print(f"Question {question + 1}: What is {number1} + {number2}?")

                        # Make error handling if the menu does not meet the requirements
                        try:
                            answer = int(input('Answer: '))
                            if answer == (number1 + number2):
                                score += 20
                                print('Yay, correct!')
                                sleep(1)
                            else:
                                print('Sorry, that is incorrect. The answer is', number1 + number2)
                                sleep(1)

                        except ValueError:
                            print('There seems to be a mistake in the input')
                            sleep(1)

                        print('')

                    print('\nYour total score is', score, '\n')

                # Exit from sub menu
                elif select_menu == 3:
                    menu_addition = False

                # End program
                elif select_menu == 4:
                    print('Thank you for playing this quiz. See you next time!')
                    sleep(1)
                    exit()

            """
            END OF MENU ADDITION
            """

        # Selecting the second menu / menu 2
        elif select_menu == 2:
            """
           MENU SUBTRACTION
           - All questions given are only subtraction
           - For subtraction mode, the answer to the given question cannot be negative.
           """
            menu_subtraction = True
            print("You've selected subtraction mode, please select the desired quiz mode")

            while menu_subtraction:
                print('Select quiz:')
                score = 0

                # Print menu
                for number, mix_menu in enumerate(list_mix_menu):
                    print(str(number + 1) + '.', mix_menu)

                # Make error handling if the menu does not meet the requirements
                try:
                    select_menu = int(input('Enter quiz mode: '))

                    if select_menu > 4:
                        raise ValueError

                except ValueError:
                    print('There seems to be a mistake in the input')
                    sleep(1)

                print('')

                # Select sub menu 1
                if select_menu == 1:
                    """
                    FREE QUIZ MENU SUBTRACTION
                    """

                    enter_quiz = True

                    while enter_quiz:
                        # Search random number between 1 and 10
                        number1 = randint(1, 10)
                        number2 = randint(1, 10)

                        # Look for values from number 1 to greater than number 2
                        while number1 < number2:
                            number1 = randint(1, 10)
                            number2 = randint(1, 10)

                        print(f'What is {number1} - {number2}?')
                        answer = input('Answer: ')

                        # Make error handling if the menu does not meet the requirements
                        try:
                            if answer.strip() == 'end quiz':
                                enter_quiz = False
                            elif int(answer) != (number1 - number2):
                                print('Sorry, that is incorrect. The answer is', number1 - number2)
                                sleep(1)
                            elif int(answer) == (number1 - number2):
                                print('Yay, correct!')
                                sleep(1)
                            else:
                                raise ValueError
                        except ValueError:
                            print('There seems to be a mistake in the input')
                            sleep(1)

                        print('')

                # Select sub menu 2
                elif select_menu == 2:
                    """
                    QUIZ 5 MENU SUBTRACTION
                    """
                    # Make 5 questions
                    for question in range(5):
                        # Search random number
                        number1 = randint(1, 10)
                        number2 = randint(1, 10)

                        # Look for the value of number1 until it is greater than number2
                        while number1 < number2:
                            number1 = randint(1, 10)
                            number2 = randint(1, 10)

                        print(f"Question {question + 1}: What is {number1} - {number2}?")

                        # Make error handling if the menu does not meet the requirements
                        try:
                            answer = int(input('Answer: '))
                            if answer == (number1 - number2):
                                score += 20
                                print('Yay, correct!')
                                sleep(1)
                            else:
                                print('Sorry, that is incorrect. The answer is', number1 - number2)
                                sleep(1)

                        except ValueError:
                            print('There seems to be a mistake in the input')
                            sleep(1)

                        print('')

                    print('\nYour total score is', score, '\n')

                # Exit from sub menu
                elif select_menu == 3:
                    menu_subtraction = False

                # End program
                elif select_menu == 4:
                    print('Thank you for playing this quiz. See you next time!')
                    sleep(1)
                    exit()
                else:
                    raise ValueError

            """
            END OF MENU SUBTRACTION
            """

        # Selecting the third menu / menu 3
        elif select_menu == 3:
            """
            MENU MIX
            - The questions are given randomly between Addition or Subtraction.
            - If a subtraction question is given, the answer to the given question cannot be negative
            """
            menu_mix = True
            print("You've selected mix mode, please select the desired quiz mode")

            while menu_mix:

                print('Select quiz:')
                score = 0

                # Print menu
                for number, mix_menu in enumerate(list_mix_menu):
                    print(str(number + 1) + '.', mix_menu)

                # Make error handling if the menu does not meet the requirements
                try:
                    select_menu = int(input('Enter quiz mode: '))
                    if select_menu > 4:
                        raise ValueError

                except ValueError:
                    print('There seems to be a mistake in the input')
                    sleep(1)

                print('')

                # Select sub menu 1
                if select_menu == 1:
                    """
                    FREE QUIZ MENU SUBTRACTION
                    """

                    enter_quiz = True

                    while enter_quiz:
                        # Search random numbers between 1 and 10
                        number1 = randint(1, 10)
                        number2 = randint(1, 10)

                        # If number 1 is greater than number 2 then do the addition
                        if number1 > number2:
                            print(f'What is {number1} + {number2}?')
                            answer = input('Answer: ')

                            try:
                                if answer.strip() == 'end quiz':
                                    enter_quiz = False
                                elif int(answer) != (number1 + number2):
                                    print('Sorry, that is incorrect. The answer is', number1 + number2)
                                    sleep(1)
                                elif int(answer) == (number1 + number2):
                                    print('Yay, correct!')
                                    sleep(1)
                                else:
                                    raise ValueError
                            except ValueError:
                                print('There seems to be a mistake in the input')
                                sleep(1)

                            print('')

                        # If number 2 is greater than number 1 then do the subtraction
                        else:
                            print(f'What is {number2} - {number1}?')
                            answer = input('Answer: ')

                            try:
                                if answer.strip() == 'end quiz':
                                    enter_quiz = False
                                elif int(answer) != (number2 - number1):
                                    print('Sorry, that is incorrect. The answer is', number2 - number1)
                                    sleep(1)
                                elif int(answer) == (number2 - number1):
                                    print('Yay, correct!')
                                    sleep(1)
                                else:
                                    raise ValueError
                            except ValueError:
                                print('There seems to be a mistake in the input')
                                sleep(1)

                            print('')

                # Select sub menu 2
                elif select_menu == 2:
                    """
                    QUIZ 5 MENU MIX
                    """

                    # Make Questions 5 times
                    for question in range(1, 6, 1):
                        # Search random number between 1 and 10
                        number1 = randint(1, 10)
                        number2 = randint(1, 10)

                        # If number 1 is greater than number 2 then do the addition
                        if number1 >= number2:

                            print(f"Question {question}: What is {number1} + {number2}?")

                            try:
                                answer = int(input('Answer: '))
                                if answer == (number1 + number2):
                                    score += 20
                                    print('Yay, correct!')
                                    sleep(1)
                                else:
                                    print('Sorry, that is incorrect. The answer is', number1 + number2)
                                    sleep(1)

                            except ValueError:
                                print('There seems to be a mistake in the input')
                                sleep(1)

                            print('')

                        # If number 2 is greater than number 1 then do the subtraction
                        elif number2 >= number1:
                            print(f"Question {question}: What is {number2} - {number1}?")

                            try:
                                answer = int(input('Answer: '))
                                if answer == (number2 - number1):
                                    score += 20
                                    print('Yay, correct!')
                                    sleep(1)
                                else:
                                    print('Sorry, that is incorrect. The answer is', number2 - number1)
                                    sleep(1)

                            except ValueError:
                                print('There seems to be a mistake in the input')
                                sleep(1)

                            print('')

                    print('\nYour total score is', score, '\n')

                # Exit from sub menu
                elif select_menu == 3:
                    menu_mix = False

                # End program
                elif select_menu == 4:
                    print('Thank you for playing this quiz. See you next time!')
                    sleep(1)
                    exit()

            """
            END OF MENU MIX
            """

        # End program
        elif select_menu == 4:
            print('Thank you for playing this quiz. See you next time!')
            sleep(1)
            exit()
