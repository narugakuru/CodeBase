#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int mian() {

	string astr= "beijing";
	string bstr = "BeiJing";

//	cin >> astr >> bstr;

	//        1�����Ȳ�ͬ
	if (astr.length() != bstr.length()) {
		cout << "1";
	}
	//        2����ȫ��ͬ
	else if ((astr.length() == bstr.length())&&(astr.size()==bstr.size())) {
		cout << "2";
	}
	//        3��������ͬ����Сд��һ��
	else if (astr.length() == bstr.length()) {
		transform(astr.begin(), astr.end(), astr.begin(), ::tolower);
		transform(bstr.begin(), bstr.end(), bstr.begin(), ::tolower);
		if(astr.size() == bstr.size())
			cout << "3";
	}
	//        4������������ͬ
	else if ((astr.length() == bstr.length())&&( astr.size() == bstr.size())) {
		cout << "4";
	}

	return 0;

}
//        1�����Ȳ�ͬ
//        2����ȫ��ͬ
//        3��������ͬ����Сд��һ��
//        4������������ͬ



