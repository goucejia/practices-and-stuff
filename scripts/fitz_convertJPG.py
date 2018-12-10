'''
convert pdf file to png

'''

import os, glob
import fitz
from PIL import Image

# filepath = "FILEPATH"
dir_path = "./pdfset"
target_txt_dir = "./generated_txt/"  # init result directory (if the info of pdf height and width is needed)
target_png_dir = "./generated_png/"
if not os.path.exists(target_txt_dir):
    os.makedirs(target_txt_dir)
if not os.path.exists(target_png_dir):
    os.makedirs(target_png_dir)

for curfile in glob.glob(os.path.join(dir_path, "*.pdf")):
    filepath = (curfile.split("/")[-1]).split(".")[0]
    # print(filepath)

    doc = fitz.open(curfile)
    print("Document '%s' has %i pages." % (doc.name, doc.pageCount))

    # toc = doc.getToC()
    index = 0
    output = ""
    while index < doc.pageCount:
        page = doc.loadPage(index)
        pm = page.getPixmap()
        img_filepath = target_png_dir+filepath+"_"+str(index+1)+".png"
        pm.writePNG(img_filepath)
        im = Image.open(img_filepath)
        new_width, new_height = im.size
        try:
            p = Image.new('RGBA' , im.size, (255, 255, 255))  # adding whites to the padding area
            p.paste(im, (0, 0, new_width, new_height), im)
            p.save(img_filepath)
        except:
        	pass

        height = pm.height
        width = pm.width
        output = str(width) + "," + str(height)
        txt_filepath = target_txt_dir+filepath+"_"+str(index+1)+".txt"
        tf = open(txt_filepath, "w+")
        tf.write(output)
        tf.close()
        index += 1
    doc.close()