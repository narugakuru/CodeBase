#include<stdio.h>
#include<string.h>
int main(){
char a[10],b[10];
int c,d,t=0,i;
gets(a);
gets(b);
c=strlen(a);
d=strlen(b);
if(c!=d){
printf(¡°1\n¡±);
}
else
{
for(i=0;i<c;i++){
if(a[i]==b[i]){
t=2;
}
else
if(a[i]==b[i]||a[i]==b[i]-32||a[i]==b[i]+32){
t=3;
}
else
if(a[i]!=b[i]){
t=4;
break;
}
}

}
if(t2)
printf(¡°2\n¡±);
if(t3)
printf(¡°3\n¡±);
if(t==4)
printf(¡°4\n¡±);
return 0;
}

