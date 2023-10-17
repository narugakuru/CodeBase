#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int mian() {

	string astr= "beijing";
	string bstr = "BeiJing";

//	cin >> astr >> bstr;

	//        1，长度不同
	if (astr.length() != bstr.length()) {
		cout << "1";
	}
	//        2，完全相同
	else if ((astr.length() == bstr.length())&&(astr.size()==bstr.size())) {
		cout << "2";
	}
	//        3，长度相同，大小写不一致
	else if (astr.length() == bstr.length()) {
		transform(astr.begin(), astr.end(), astr.begin(), ::tolower);
		transform(bstr.begin(), bstr.end(), bstr.begin(), ::tolower);
		if(astr.size() == bstr.size())
			cout << "3";
	}
	//        4，仅仅长度相同
	else if ((astr.length() == bstr.length())&&( astr.size() == bstr.size())) {
		cout << "4";
	}

	return 0;

}
//        1，长度不同
//        2，完全相同
//        3，长度相同，大小写不一致
//        4，仅仅长度相同



