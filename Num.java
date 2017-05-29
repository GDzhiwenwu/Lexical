# Lexical
//编译原理词法分析器Java代码
package com.data.Lex;

public class Num extends Token{  
    public final int value;  
      
    public Num(int v) {  
        super(Tag.NUM);  
        this.value = v;  
    }  
      
    public String toString() {  
        return  "" + value;  
    }  
}  
