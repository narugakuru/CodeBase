# def get_formatted_name(first, last):
#     """Generate a neatly formatted full name."""
#     full_name = first + " " + last
#     return full_name.title()


def get_formatted_name(first, last, middle=""):
    """生成整洁的姓名"""
    if middle:
        full_name = first + " " + middle + " " + last
    else:
        full_name = first + " " + last
    return full_name.title()


def main():
    print("Enter 'q' at any time to quit.")
    while True:
        first = input("\nPlease give me a first name: ")
        if first == "q":
            break
        last = input("Please give me a last name: ")
        if last == "q":
            break
        formatted_name = get_formatted_name(first, last)
        print("\tNeatly formatted name: " + formatted_name + ".")


if __name__ == "__main__":
    main()
