from bisect import *

def rearrange(arr):
    for i in range(len(arr)-1):
        if(arr[i]>arr[i+1]):
            arr[i],arr[i+1]=arr[i+1],arr[i]
    return arr


def bufferAlgo(x,n,b):

    y=b[1:len(b)-1]
    buffer = list(map(int,y.strip().split(",")))

    if(buffer==[]):
        buffer.append(x)
        temp=buffer[:]
    else:
        if(x>buffer[0]):
            insert_index=bisect_left(buffer,x)
            if(insert_index==len(buffer)):
                buffer.append(x)
                temp=buffer[:]
            elif(buffer[insert_index]<x):
                insert_index+=1
                temp = buffer[:insert_index]
                temp.append(x)
                temp.extend(buffer[insert_index:])
            else:
                temp=buffer[:insert_index]
                temp.append(x)
                temp.extend(rearrange(buffer[insert_index:]))
        else:
            streak=0
            flag=0
            # print("hi")
            for i in range(len(buffer)-1):
                print("kk")
                print(streak)
                print(buffer[i])
                if(buffer[i]<buffer[i+1]):
                    # print("bkah")
                    if(buffer[i]>=buffer[0] or buffer[i]>buffer[1]):
                        streak+=1
                        # print("streak")
                        # print(streak)
                        if(streak==n):
                            print(buffer[i])

                            # streak=0
                            # print(buffer[])
                            if(buffer[i]>buffer[i+1]):
                                print(i)
                                # i=i+1
                                print(i)
                                print(streak)
                                streak=0
                                print(streak)
                                continue
                            if(streak==n):
                                insert_index=i+1
                                temp = buffer[:insert_index]
                                temp.append(x)
                                temp.extend(buffer[insert_index:])
                                flag=1
                                streak=0
                                break
                else:
                    streak=0
            if(flag==0):
                buffer.append(x)
                temp=buffer[:]
    return temp