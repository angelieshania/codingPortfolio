from string import punctuation  # import library string punctuation
import htmlFunctions  # import module htmlFunctions


def remove_punctuation(myList):  # create remove_punctuation function
    newList = []  # accommodate new word value
    for theWord in myList:
        while(True):
            if(len(theWord) == 0): break  # if theWord is empty, stop looping
            if(theWord[0] in punctuation): theWord = theWord[1:]  # will remove word if word in punctuation
            elif(theWord[-1] in punctuation): theWord = theWord[:-1]
            else: break
        newList.append(theWord)  # append a new word, to new list
    return newList  # bring back newList value


def remove_word(words):  # create remove_word function
    stop_word = ''

    with open('stopwords.txt', 'r') as stop_words_file:  # open stopwords file as stop_words_file
        for char in stop_words_file:
            stop_word += char  # append char in stopwords file to stop_word variable

    stop_word = stop_word.split()  # split every word
    new_word = [char for char in words if char.lower() not in stop_word]  # remove word if the word is in stop_word

    return new_word  # bring back new_word value


def word_count(words):  # create word_count function
    count = dict()

    # If the word in count variable, value of the word will be added 1 else the value is 1
    for char in words:
        if char in count:
            count[char] += 1
        else:
            count[char] = 1

    return count  # bring back count value


if __name__ == "__main__":
    print('Program to create word cloud from a text file')
    print('-'*45)
    print('The result is stored as an HTML file,\nwhich can be displayed in a web browser.\n')

    input_file = input('Please enter the file name: ')  # request user input

    text_file = ''

    # handle if user enters the wrong file
    try:
        if input_file == 'stopwords.txt':
            raise FileNotFoundError

        with open(input_file, 'r') as file:
            for text in file:
                text_file += text.lower()
        text_file = remove_punctuation(text_file.split())  # remove punctuation
        text_file = remove_word(text_file)  # remove forbidden words

        print(input_file + ':')
        print('56 words in frequency order as (count:word) pairs\n')

        text_count = word_count(text_file)  # count the number of words
        max_length_word = 0
        sorted_text_count = dict()
        sorted_key_text_count = sorted(text_count, key = lambda k:(text_count[k], k), reverse=True)

        # sort words by value
        for key in sorted_key_text_count:
            if len(key) > max_length_word:
                max_length_word = len(key)
            sorted_text_count[key] = text_count[key]

        if len(text_count) >= 56:
            sorted_key_text_count = sorted_key_text_count[:56]
            index = 0
            # print the word with row 14 and column 4
            for row in range(14):
                for column in range(4):
                    print(str(sorted_text_count[sorted_key_text_count[index]]).rjust(2) + ':' +
                          sorted_key_text_count[index].ljust(max_length_word), end='\t')

                    index += 1

                print()
        # else:
        #     sorted_key_text_count = sorted_key_text_count[:len(sorted_key_text_count)]
        #     index = 0
        #     for row in range(len(sorted_key_text_count)//4):
        #         for column in range(4):
        #             print(str(sorted_text_count[sorted_key_text_count[index]]).rjust(2) + ':' +
        #                   sorted_key_text_count[index].ljust(max_length_word), end='\t')
        #
        #             index += 1
        #
        #         print()

        # Generate HTML File
        pairs = [(key.lower(), sorted_text_count[key]) for key in sorted(sorted_key_text_count)]

        high_count = max(pair[1] for pair in pairs)
        low_count = min(pair[1] for pair in pairs)

        body = ''

        for word, cnt in pairs[:56]:
            body = body + " " + htmlFunctions.make_HTML_word(word, cnt, high_count, low_count)

        box = htmlFunctions.make_HTML_box(body)
        cloudName = "A Word Cloud of " + input_file
        htmlFunctions.print_HTML_file(box, cloudName)

    # condition if file not found
    except FileNotFoundError:
        print('File not found!')
        
    input('\nPlease type Enter to exit . . .')

    
