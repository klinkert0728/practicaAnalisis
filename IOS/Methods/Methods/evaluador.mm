
//  Created by Daniel Klinkert on 9/13/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "evaluador.h"



double evaluarFuncion (NSString *funcion, NSString *valor){
	
	
	double enX = [valor doubleValue];
	double x;
	RVar xvar ( "x" , &x );
	unichar charOne;
	char s[500]="";
	int i = 0;
	for(int pos=0;pos <[funcion length]; pos++) {
		charOne = [funcion characterAtIndex:pos];
		s[i] = charOne;
		i++;
	}
	RVar* vararray[1]; vararray[0]=&xvar;
	ROperation op ( s, 1, vararray );
	x=enX;
	return op.Val();
}
