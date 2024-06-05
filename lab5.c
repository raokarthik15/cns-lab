#include<stdio.h>
#include<stdlib.h>

int IP[8] = {2,6,3,1,4,8,5,7};
int IP_inv[8] = {4,1,3,5,7,2,8,6};

int S0[4][4] = {
    {1,0,3,2},
    {3,2,1,0},
    {0,2,1,3},
    {3,1,3,2}
};

int S1[4][4] = {
    {0,1,2,3},
    {2,0,1,3},
    {3,0,1,0},
    {2,1,0,3}
};

int initial_perm(int plaintext) {
    int result = 0;
    for(int i = 0; i < 8; i++)
        result |= ((plaintext >> (8-IP[i])) & 1) << (7 - i);
    return result;
}

int inv_initial_perm(int ciphertext) {
    int result = 0;
    for(int i = 0; i < 8; i++)
        result |= ((ciphertext >> (8-IP_inv[i])) & 1) << (7 - i);
    return result;
}

int s_box_sub(int val, int s_box[4][4]) {
    int row = ((val & 0b1000) >> 2) | (val & 0b0001);
    int col = (val & 0b0110) >> 1;
    return s_box[row][col];
}

int main() {

    int plaintext = 0b00001111;
    printf("Plain Text : %x\n", plaintext);
    
    int ciphertext = initial_perm(plaintext);
    printf("Cipher Text : %x\n", ciphertext);

    int s_box_value = 0b1101;
    int s_box_result = s_box_sub(s_box_value, S0);

    printf("s_box result: %x\n", s_box_result);

    int decrypted_text = inv_initial_perm(ciphertext);
    printf("Decrypted Text: %x\n", decrypted_text);
    
    return 0;
}