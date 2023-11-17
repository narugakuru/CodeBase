#include <iostream>
#include <string>

int main()
{
    const int MAX_AGE = 90;
    int *a = new int;
    std::cout << a << std::endl;
    std::cout << &MAX_AGE << std::endl;

    a = (int *)&MAX_AGE;
    std::cout << a << std::endl;
    std::cout << &MAX_AGE << std::endl;

    *a = 31;
    std::cout << *a << std::endl;
    std::cout << MAX_AGE << std::endl;
    std::cout << a << std::endl;
    std::cout << &MAX_AGE << std::endl;
    std::cin.get();
}
