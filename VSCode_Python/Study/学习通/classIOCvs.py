import random

file = open("1907伪2.cvs", "r", encoding="utf-8")
lines = []

for line in file:
    randnum = str(random.randint(60, 100))
    line = line + "," + randnum
    print(line)
    line = line.replace("\n", "")
    lines.append(line.split(","))

print(lines)
file.close()

file_new = open("1907伪.cvs", "w+", encoding="utf-8")
for line in lines:
    file_new.write(",".join(line) + "\n")

file_new.close()