import os

xml_path = "./ICDAR_formula_label/"
img_info_path = "./generated_txt/"
target_path = "./converted_txt/"  # init result directory
if not os.path.exists(target_path):
    os.makedirs(target_path)
# img_info_path = "./generated_txt/"

for rt, subdir, files in os.walk( xml_path ):
	for file in files:
		height_info = img_info_path + file
		try:
			with open(height_info) as f:
				data = f.read()
				height = data.split(",")[1]
				print(height)
		except:
			pass

		# height = 792   # if dataset img height is constant

		with open(xml_path+file) as f:
			output = ""
			line = f.readline()
			while line:
				data = line.split(",")
				left = int(data[0])
				right = int(data[1])
				top = data[2]
				bot = (data[3].split("\t"))[0]
				# print(bot)

				new_top = height-int(top)
				new_bot = height-int(bot)

				tempStr = str(left) + "," + str(right) + "," + str(new_top) + "," + str(new_bot) + "\t table" + "\n"
				# print(tempStr)
				output += tempStr
				line = f.readline()

			new_file_path = target_path + file
			tf = open(new_file_path, "w+")
			tf.write(output)
			tf.close()
