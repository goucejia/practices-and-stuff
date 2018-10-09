"""
Note:

This script is to generate according txt file for each png files from yolo results.
Put under same directory and run. Invalid data (with missing information element) will be ignored.
"""

import re
import math
import os


def peek_line(f):
    pos = f.tell()
    line = f.readline()
    f.seek(pos)
    return line


path = "./txt_gene/"  # init result directory
if not os.path.exists(path):
    os.makedirs(path)

with open("yolov2_predict_chineseImg.txt", "r") as f:
    line = f.readline()

    while line != "":
        if line[0:5] == "Enter":
            name = (((line.split(":"))[1].split("/"))[6].split("."))[0]
            filename = str(name + ".txt")
            print("processing: " + name)
            s = ""

            while (peek_line(f) != '' and peek_line(f)[0:5] != "Enter"):
                # ['formula', ' 39%\t(left_x', ' 1614   top_y', ' 4673   width', ' 1963   height', '  116)\n']
                data = f.readline().split(":")
                if len(data) != 6:
                    continue
                type = data[0]  # type
                conf = int((data[1].split("%"))[0].replace(" ", ""))/100  # confidence

                left = int((data[2].split("t"))[0].replace(" ", ""))  # coordinates
                top = int((data[3].split("w"))[0].replace(" ", ""))
                wid = int((data[4].split("h"))[0].replace(" ", ""))
                hh = int((data[5].split(")"))[0].replace(" ", ""))

                right = math.floor(left + wid)  # convert coordinates
                bot = math.floor(top + hh)

                s += str(left) + "," + str(right) + "," + str(top) + "," + str(bot) + "\t" +\
                    type + "\t" + str(conf) + "\n"
            # print(s)
            tf = open(path + filename, "w+")
            tf.write(s)
            tf.close()

        line = f.readline()

print("done")