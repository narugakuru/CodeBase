#include <stdio.h>
int main()
{
	double f[50];
	int i;
    float f37 = 24157816.000000,f36,f35;
	f[0]=0;
	f[1]=1;
	for(i=2;i<50;i++)
	{
		f[i]=f[i-1]+f[i-2];
        if(i==37) 
            f37 = f[37];
        
		if (f[i-2]!=f[i]-f[i-1]||f[i]<f[i-1])
		{
		    printf("error at %d\n",i);
	    }
	}
	for(i=0;i<50;i++)
	{
		if(i%5==0)
		    printf("\n");
		printf("f[%d]=%.0f\t",i,f[i]);
	}
    printf("%f",f37);   
	system("pause");
}