#include<stdio.h>
#include<string.h>

void main()
{
    char str[] = "Hello World";
    char str1[11]="", str2[11]="", str3[11]="";

    int i, len;
    len = strlen(str);
    printf("After applying AND operation corresponding ASCII and its values:\n");
    for(int i=0; i<len; i++) {
        str1[i] = str[i]&127;
        printf("%d = %c\n", str[i], str1[i]);
    }
    str1[11] = '\0';
    printf("Output string: %s\n", str1);
    
    printf("AFter applying XOR operation corresponding ASCII and its values:\n");
    for(int i = 0; i<len; i++){
        str2[i] = str[i]^127;
        printf("%d = %c\n", str[i], str2[i]);
    }
    str2[11] = '\0';
    printf("Output string: %s\n", str2);
    
    // printf("AFter applying OR operation corresponding ASCII and its values:\n");
    // for(int i = 0; i<len; i++){
    //     str3[i] = str[i]|127;
    //     printf("%d = %c\n", str[i], str3[i]);
    // }
    // str3[11] = '\0';
    // printf("Output string: %s\n", str3);
}
