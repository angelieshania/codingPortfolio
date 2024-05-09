def merge(left, right):
    """Assumes left and right are sorted lists.
    Returns a new sorted (by up ordering) list containing the
    same elements as (left + right) would contain."""
    
    result = [] #to create a new list
    i,j = 0, 0 #for index left list (i) and right list (j) while looping
    
    #merging one by one
    #check the elements in the right and left lists one by one, insert the smallest element into the result list
    #repeat the process until one of the left or right lists is empty
    while i < len(left) and j < len(right): # looping until it reaches the last index of one of the lists
        if left[i] <= right[j]:
            result += [left[i]]
            i += 1
        else:
            result += [right[j]]
            j += 1

    #concatenating the rest
    #condition when there is right or left list remains 
    if i < len(left):
        result.extend(left[i:])
    elif j < len(right):
        result.extend(right[j:])

    return result

def mergeSort(L):
    """Assumes L is a list,
    Returns a new sorted list (in up ordering)
    containing the same elements as L"""
    
    #base cases
    #condition if the number of input list members is only 1
    if len(L) <= 1:
        return L[:]

    #recursive cases 
    #if there is more than one member, divide the list so that there is only one member remains
    #then sort and combine the lists with the merge() function
    else: #recursive cases
        middle = len(L)//2
        left = mergeSort(L[:middle])
        right = mergeSort(L[middle:])
        return merge(left, right)

import time
def main():
    print('TP 03 DDP1 -- 2021\nImplementation of Merge Sort\n============================\n')

    #asking and validating input from user
    file1 = input('Input file name: ') 
    file2 = input('\nOutput file name: ') 

    try:
        ifile = open(file1, 'r') #reading input files
        ofile = open(file2,'w') #to make the output file can be written

        #constructing the list of numbers
        integersList = []
    
        #add each number in the file to the list
        for line in ifile:
            integersList.append(int(line))
        
        #to count time
        t1 = time.time()
        t = time.process_time()
        
        print("\nSorting in progress ...")
        #run the mergeSort function to sort the list
        sortedNumbers = mergeSort(integersList)

        #count time
        cpu_time = time.process_time() - t
        duration = time.time() - t1
        
        #writing the sorted numbers to the output file
        for number in sortedNumbers:
            print(number, file = ofile)

        #close file and closing message
        ifile.close()
        ofile.close()
        print("CPU time of the Merge-sort: ", cpu_time)
        print("Clock time of the Merge-sort: ", duration)
    
    #condition if the input file is not found
    except FileNotFoundError:
        print('Input file not found.')

if __name__ == '__main__':
    main()
