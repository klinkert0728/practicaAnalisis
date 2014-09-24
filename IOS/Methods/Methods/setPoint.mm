//
//  setPoint.m
//  Methods
//
//  Created by Daniel Klinkert on 9/20/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "setPoint.h"

@implementation setPoint

- (void)initWithFunction:(NSString *)funct functionG:(NSString *)funcG x0:(double)valorInicial iter:(int)ite tol:(double)toler{
    
    function = funct;
    functionG= funcG;
    x0= valorInicial;
    iter = ite;
    tol = toler;
    [self setPoint];
    
    
}

- (void)setPoint{
    
    NSString *x0Prov = [NSString stringWithFormat:@"%f",x0];
    double y = evaluarFuncion(function, x0Prov);
    double error = tol + 1;
    double cont = 0;
    
    while ((y != 0) && (error > tol) && (cont < iter)) {
        x0Prov = [NSString stringWithFormat:@"%f", x0];
        double x1 = evaluarFuncion(functionG, x0Prov);
        NSString *x1Prov = [NSString stringWithFormat:@"%f", x1];
        y = evaluarFuncion(function, x1Prov);
        error = fabs(x1 - x0);
        x0 = x1;
        cont++;
    }
    
    if (y == 0) {
        NSLog(@"%f es raiz\n", x0);
        
        UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz Encontrada" message:[NSString stringWithFormat:@"%f es raiz\n", x0] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        [alert show];
        
       // [resultados setResultados:x0 :error];
    } else {
        if (error < tol) {
            NSLog(@"%f es raiz con %e", x0, tol);
            UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz Encontrada" message:[NSString stringWithFormat:@"%f es raiz con %e", x0, tol] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [alert show];
           // [resultados setResultados:x0 :error];
        } else {
            NSLog(@"no se hallo raiz con %d iteraciones\n", iter);
            
            UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Error" message:[NSString stringWithFormat:@"no se hallo raiz con %d iteraciones", iter] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            
            [alert show];

           // [resultados setResultado:iter];
        }
        
    }
    
    
    
}

@end
