//
//  busquedaIncremental.m
//  Methods
//
//  Created by Daniel Klinkert on 9/13/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "busquedaIncremental.h"

@implementation busquedaIncremental
    
   
- (void)initMethod:(NSString *)funcion
             delta:(NSString *)delta
          xInicial:(NSString *)xInicial
       iteraciones:(NSString *)iteraciones{
    
    
    func = funcion;
	X0 = xInicial;
	delt = [delta doubleValue];
	iter = [iteraciones intValue];
    
	[self busquedaIncremental];
}

- (void)busquedaIncremental{
    
    double Y0 = evaluarFuncion(func, X0);
	double x0Prov = 0.0;
	double x1Prov = 0.0;
	
	if (Y0 == 0) {
		NSLog(@"%@ es la raiz\n", X0);
        //return [NSString stringWithFormat:@"%@ es la raiz\n", X0];
		//[resultados setResultado: [X0 doubleValue]];
	} else {
		x0Prov = [X0 doubleValue];
		x1Prov = x0Prov + delt;
		X1 = [NSString stringWithFormat:@"%f", x1Prov];
		double Y1 = evaluarFuncion(func, X1);
		int cont = 1;
		
		while ((Y0*Y1 > 0) && (cont < iter)) {
			X0 = X1;
			Y0 = Y1;
			x0Prov = [X0 doubleValue];
			x1Prov = x0Prov + delt;
			X1 = [NSString stringWithFormat:@"%f", x1Prov];
			Y1 = evaluarFuncion(func, X1);
			cont++;
		}
		
		if (Y1 == 0) {
            NSLog(@"%@ es la raiz\n", X1);
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:[NSString stringWithFormat:@"%@ es la raiz", X1] delegate:nil cancelButtonTitle:@"ok" otherButtonTitles:nil, nil];
            [alert show];
           // return [NSString stringWithFormat:@"%@ es la raiz\n", X1];
			//
			//[resultados setResultado:[X1 doubleValue]];
		} else if (Y0*Y1 < 0) {
			NSLog(@"[%@, %@] definen intervalo\n", X0, X1);
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:[NSString stringWithFormat:@"[%@, %@] definen intervalo", X0, X1] delegate:nil cancelButtonTitle:@"ok" otherButtonTitles:nil, nil];
            [alert show];
            //return [NSString stringWithFormat:@"[%@, %@] definen intervalo\n", X0, X1];
			//[resultados setResultados:[X0 doubleValue] :[X1 doubleValue]];
		} else {
			NSLog(@"Error(0)");
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:[NSString stringWithFormat:@"Error"] delegate:nil cancelButtonTitle:@"ok" otherButtonTitles:nil, nil];
            [alert show];
            //return @"Error(0)";
			//[resultados setErrorMsg:@"Error(0)"];
		}
	}
    //[alert show];
       // return @"error";
}


@end
