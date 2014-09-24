//
//  multipleRoots.m
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "multipleRoots.h"

@implementation multipleRoots

- (void)initWithFunction:(NSString*)funct
   functionFirstDerivate:(NSString*)dFunc
  functionSecondDerivate:(NSString*)ddFunc
valorInicialMultiplesRaices:(double)xRaicMult
              tolerancia:(double)tolRaicMult
             iteraciones:(int)iterRaicMult{
    
    funcion = funct;
    dFuncion = dFunc;
    ddFuncion= ddFunc;
    x1= xRaicMult;
    tol=tolRaicMult;
    iter = iterRaicMult;
    
    [self multipleRoots];
}

- (void)multipleRoots{
    
    
    NSString *x1Prov = [NSString stringWithFormat:@"%f", x1];
    double y = evaluarFuncion(funcion, x1Prov);
    double dy = evaluarFuncion(dFuncion, x1Prov);
    double ddy = evaluarFuncion(ddFuncion, x1Prov);
    
    double error = tol + 1;
    double den = pow(dy, 2) - (y * ddy);
    double cont = 0;
    
    while ((y != 0) && (error > tol) && (den != 0) && (cont < iter)) {
        double Xant = x1;
        x1 = Xant - ((y*dy)/den);
        x1Prov = [NSString stringWithFormat:@"%f", x1];
        y = evaluarFuncion(funcion, x1Prov);
        dy = evaluarFuncion(dFuncion, x1Prov);
        ddy = evaluarFuncion(ddFuncion, x1Prov);
        den = pow(dy, 2) - (y * ddy);
        error = fabs(x1 - Xant);
        cont++;
    }
    
    if (y == 0) {
        NSLog(@"%f es raiz\n", x1);
        
        UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz encontrada" message:[NSString stringWithFormat:@"%f es raiz", x1] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        [alert show];
        
        
        
       // [resultados setResultado:x1];
    } else if (error < tol) {
        NSLog(@"%f es raiz con %e\n", x1, tol);
        
        UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz encontrada" message:[NSString stringWithFormat:@"%f es raiz con %e", x1, tol] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        [alert show];
       // [resultados setResultados:x1 :error];
    } else if (den == 0) {
        NSLog(@"Hay division por 0\n");
        
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Error" message:@"División por Cero" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        
        [alert show];

       // [resultados setErrorMsg:@"Hay division por 0\n"];
    } else {
        NSLog(@"No se hallo raiz con %d iteraciones\n", iter);
        UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz encontrada" message:[NSString stringWithFormat:@"No se hallo raiz con %d iteraciones", iter] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
        [alert show];
        
       // [resultados setErrorMsg:temp];
    }
    
    
    
    
    
}

@end
