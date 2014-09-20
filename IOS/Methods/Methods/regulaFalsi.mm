//
//  regulaFalsi.m
//  Methods
//
//  Created by Daniel Klinkert on 9/20/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "regulaFalsi.h"

@implementation regulaFalsi

- (void)initWithFunction:(NSString *)funcion
           valorInferior:(NSString *)xInf
           valorSuperior:(NSString *)xSup
              tolerancia:(double)toler
             iteraciones:(int)ite{
    
    
    xi = [xInf doubleValue];
    xs = [xSup doubleValue];
    tol  = toler ;
    iter = ite;
    func = funcion;
    [self regulaFalsi];
    
}

- (void)regulaFalsi{
    
    NSString *xiTemp = [NSString stringWithFormat:@"%f", xi];
    NSString *xsTemp = [NSString stringWithFormat:@"%f", xs];
    double yi = evaluarFuncion(func, xiTemp);
    double ys = evaluarFuncion(func, xsTemp);
    
    
    if (yi == 0) {
        NSLog(@"%f es la raiz\n", xi);
       // [resultados setResultados:xi :0.0];
    } else if (ys == 0) {
        NSLog(@"%f es la raiz\n", xs);
        //[resultados setResultados:xs :0.0];
    } else if (yi*ys > 0) {
        NSLog(@"[%f, %f] no tiene raices", xi, xs);
       // [resultados setResultados:0.0 :0.0];
    } else {
        double xr = xi - ((yi * (xi - xs))/(yi - ys));
        NSString *xrTemp = [NSString stringWithFormat:@"%f", xr];
        double yr = evaluarFuncion(func, xrTemp);
        double error = tol + 1;
        double cont = 1;
        
        while ((yr != 0) && (error > tol) && (cont < iter)) {
            double xaux = xr;
            
            if (yi * yr > 0) {
                xi = xr;
                yi = yr;
            } else {
                xs = xr;
                ys = yr;
            }
            
            xr = xi - ((yi * (xi - xs))/(yi - ys));
            xrTemp = [NSString stringWithFormat:@"%f", xr];
            yr = evaluarFuncion(func, xrTemp);
            error = fabs(xr - xaux);
            cont++;
        }
        
        if (yr == 0) {
            
            UIAlertView * alert = [[UIAlertView alloc]initWithTitle:@"Raiz Encontrada" message:[NSString stringWithFormat:@"%f es raiz", xr] delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [alert show];

            NSLog(@"%f es raiz\n", xr);
            //[resultados setResultados:xr :error];
        } else if (error < tol) {
            
            UIAlertView * alert = [[UIAlertView alloc]initWithTitle:@"Raiz Encontrada" message:[NSString stringWithFormat:@"%f es raiz con %e", xr, tol] delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [alert show];
            
            NSLog(@"%f es raiz con %e\n", xr, tol);
            //[resultados setResultados:xr :error];
        } else if (cont == iter) {
            NSLog(@"No se hallo raiz con %d iteraciones\n", iter);
            
            UIAlertView * alert = [[UIAlertView alloc]initWithTitle:@"Raiz no Encontrada" message:[NSString stringWithFormat:@"No se hallo raiz con %d iteraciones", iter] delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [alert show];

            //[resultados setResultado:iter];
        } else {
            UIAlertView * alert = [[UIAlertView alloc]initWithTitle:@"Raiz no Encontrada" message:@"Error" delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [alert show];
        }
    }
    
}

@end
