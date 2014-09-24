//
//  NewtonRaphson.m
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "NewtonRaphson.h"

@implementation NewtonRaphson



- (void)initWithFunction:(NSString *)func functionDerivate:(NSString*)dFunc valorInicial:(double)x0 tolerancia:(double)tolNew iteraciones:(int) iterNEW{
    
    
    funcion = func;
    dFuncion= dFunc;
    X0 = x0;
    tol = tolNew;
    iter = iterNEW;
    
    [self NewtonRaphson];
    
}


- (void)NewtonRaphson{
    
    NSString *X0Prov = [NSString stringWithFormat:@"%f", X0];
    double y = evaluarFuncion(funcion, X0Prov);
    double dy = evaluarFuncion(dFuncion, X0Prov);
    double error = tol + 1;
    int cont = 1;
    
    while ((y != 0) && (dy != 0) && (error > tol) && (cont < iter)) {
        double xn = X0 - y/dy;
        NSString *xnProv = [NSString stringWithFormat:@"%f", xn];
        y = evaluarFuncion(funcion, xnProv);
        dy = evaluarFuncion(dFuncion, xnProv);
        error = fabs(xn - X0);
        X0 = xn;
        cont++;
    }
    
    if (y == 0) {
        NSLog(@"%f es raiz\n", X0);
        
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Raiz Encontrada" message:[NSString stringWithFormat:@"%f es raiz ", X0] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        
        [alert show];
        //[resultados setResultados:X0 :error];
    } else if (dy == 0) {
        NSLog(@"Division por cero");
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Error" message:@"División por Cero" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        
        [alert show];
    } else {
        if (error < tol) {
            NSLog(@"%f es raiz con %e", X0, tol);
            
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Raiz Encontrada" message:[NSString stringWithFormat:@"%f es raiz con %e", X0, tol] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            
            [alert show];
            
            // [resultados setResultados:X0 :error];
        } else {
            NSLog(@"no se hallo raiz con %d iteraciones\n", iter);
            
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"No hay Raiz" message:[NSString stringWithFormat:@"no se hallo raiz con %d iteraciones", iter] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            
            [alert show];

            //[resultados setResultado:iter];
        }
    }
}


@end
