import random

input_count = 0
i = random.randint(0, 9)
while True:
    temp = int(input("input please："))
    input_count += 1
    if temp == i:
        print("yes")
        break
    else:
        print("no")

print("input_count is %d" % input_count)
