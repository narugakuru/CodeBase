package main

import (
	"fmt"
	"test/src/morestrings"
	"test/src/myutil"

	// "morestrings" //wdnmd不需要导入
	"github.com/google/go-cmp/cmp"
)

func main() {
	test1()
	fmt.Printf("myutil.Add(4, 8): %v\n", myutil.Add(4, 8))
}

func ReverseRunes(s string) string {
	r := []rune(s)
	for i, j := 0, len(r)-1; i < len(r)/2; i, j = i+1, j-1 {
		r[i], r[j] = r[j], r[i]
	}
	return string(r)
}

func test1() {
	var b, c int = 1, 2
	fmt.Println(b, c)

	fmt.Println("Hello, World!")
	// %d 表示整型数字，%s 表示字符串
	var stockcode = 123
	var enddate = "2020-12-31"
	var url = "Code=%d&endDate=%s"
	var target_url = fmt.Sprintf(url, stockcode, enddate)
	fmt.Println(target_url)

	fmt.Println(morestrings.ReverseRunes("!oG ,olleH"))
	fmt.Println(cmp.Diff("Hello World", "Hello Go"))
}
