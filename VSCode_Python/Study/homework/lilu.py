benjin = 0

for i in range(8):
    if i < 4:
        benjin = benjin + (1.4 * 5)

    benjin = benjin * (1 + 0.0275 * 5)

print("{:.3f}万人民币".format(benjin))
