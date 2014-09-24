//
//  secant.m
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "secant.h"

@implementation secant


- (void)initWithFunction:(NSString*)function valorIniciar:(double)x0Sec valorSgte:(double)x1Sec tolerancia:(double)toler iteraciones:(int) iter{
    
    func = function;
    x0 =x0Sec;
    x1 =x1Sec;
    tol=toler;
    ite= iter;
    
    [self secante];
    
}

- (void)secante{
    
    
    NSString *x0Prov = [NSString stringWithFormat:@"%f", x0];
    NSString *x1Prov = [NSString stringWithFormat:@"%f", x1];
    double y0 = evaluarFuncion(func, x0Prov);
    double y1 = evaluarFuncion(func, x1Prov);
    
    if (y0 == 0) {
        NSLog(@"%f es raiz", x0);
        //[resultados setResultado:x0];
    } else {
        double error = tol + 1;
        double div = y1 - y0;
        int cont = 1;
        
        while (y1 != 0 && error > tol && div != 0 && cont < ite) {
            double x2 = x1 - (y1 * (x1 - x0)) / div;
            y0 = y1;
            NSString *x2Prov = [NSString stringWithFormat:@"%f", x2];
            y1 = evaluarFuncion(func, x2Prov);
            error = fabs(x2 - x1);
            cont++;
            x0 = x1;
            x1 = x2;
            div = y1 - y0;
        }
        
        if (y1 == 0) {
            NSLog(@"%f es raiz\n", x0);
            
            UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz Encontrada" message:[NSString stringWithFormat:@"%f es raiz", x0] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [alert show];
            
            //[resultados setResultados:x0 :error];
        } else if (div == 0) {
            NSLog(@"Division por cero");
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Error" message:@"División por Cero" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            
            [alert show];

            //[resultados setResultados:x0 :error];
        } else {
            if (error < tol) {
                NSLog(@"%f es raiz con %e", x0, tol);
                
                UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz Encontrada" message:[NSString stringWithFormat:@"%f es raiz con %e", x0, tol] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
                [alert show];
                
               // [resultados setResultados:x0 :error];
            } else {
                NSLog(@"no se hallo raiz con %d iteraciones\n", ite);
                
                UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"No hay Raiz" message:[NSString stringWithFormat:@"No se hallo raiz con %d iteraciones\n", ite] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
                
                [alert show];

                //[resultados setResultado:ite];
            }
        }
    }
    
}

@end
