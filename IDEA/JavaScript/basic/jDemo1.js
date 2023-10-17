function LeapYear() {
	var year = document.getElementById("num").value;
	if (year % 100 % 4 == 0) {
		alert(year + "是闰年");
	} else {
		alert(year + "不是闰年")
	}
}

/* function LeapYear (year) {
	
	if(year%100%4==0){
		alert(year+"是闰年");
	}
	else{
		alert(year+"不是闰年")
	}
} */
