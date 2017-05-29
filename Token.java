# Lexical
//编译原理词法分析器Java代码
package com.data.Lex;

public class Token {  
    public final int tag;  
    
    public Token(int t) {  
        this.tag = t;  
    }  
      
    public String toString() {  
        return "" + (char)tag;  
    }  
      
    public static void main(String[] args) {  
        Token tok = new Token('a');  
        System.out.println(tok);  
    }  
}
