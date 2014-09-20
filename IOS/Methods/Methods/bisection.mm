//
//  bisection.m
//  Methods
//
//  Created by Daniel Klinkert on 9/19/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "bisection.h"

@implementation bisection


- (void)initWithFunction:(NSString *)funcion valorInicial:(NSString *)xInf valorSuperior:(NSString *)xSup tolerancia:(double)toler iteraciones:(int)ite {
    
    func = funcion;
    xinf = xInf;
    xsup = xSup;
    tol = toler;
    iter = ite;
    [self biseccion];
    
}

- (void)biseccion {
    double xi = [xinf doubleValue];
    double xs = [xsup doubleValue];
    
    
    double yi = evaluarFuncion(func, xinf);
    double ys = evaluarFuncion(func, xsup);
    
    
    if (yi == 0) {
        NSLog(@"%f es la raiz\n", xi);
     //   [resultados setResultados:xi :0.0];
    } else if (ys == 0) {
        NSLog(@"%f es la raiz\n", xs);
       // [resultados setResultados:xs :0.0]; ojooo
    } else if (yi*ys > 0) {
        NSLog(@"[%f, %f] no tiene raices", xi, xs);
        //NSString *temp = [NSString stringWithFormat:@"[%f, %f] no tiene raices.", xi, xs];
        //NSString *temp = @"[%f, %f] no tiene raices.", xi, xs;
        //[resultados setErrorMsg:temp];
    } else {
        double xm = (xi+xs)/2;
        NSString* xMedio = [NSString stringWithFormat:@"%f", xm];
        NSLog(@"%@",xMedio);
        double ym = evaluarFuncion(func, xMedio);
        NSLog(@"%f",ym);
        double error = tol + 1;
        double cont = 1;
        
        while ((ym != 0) && (error > tol) && (cont < iter)) {
            double xaux = xm;
            
            if (yi*ym > 0) {
                xi = xm;
                yi = ym;
            } else {
                xs = xm;
                ys = ym;
            }
            
            xm = (xi + xs)/2;
            xMedio = [NSString stringWithFormat:@"%f", xm];
            ym = evaluarFuncion(func, xMedio);
            error = fabs(xm - xaux);
            cont++;
        }
        
        if (ym == 0) {
            //NSLog(@"%f es raiz\n", xm);
            
            NSString * xmErrorString =[NSString stringWithFormat:@"%f es raiz, con error %e",xm,error];
//            NSString * xMedioErrorString = [NSString stringWithFormat:@"%@ es raiz, con error %f",xMedio,error];
            UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz encontrada" message:[NSString stringWithFormat:@"%@",xmErrorString] delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            
            [alert show];

            NSLog(@"%f es raiz, con error %e",xm,error);
            NSLog(@"%@ es raiz, con error %f",xMedio,error);
           // [resultados setResultados:xm :error];
        } else if (error < tol) {
            
            NSString * xmErrorString =[NSString stringWithFormat:@"%f es raiz, con error %e",xm,error];
            
//            NSString * xMedioErrorString = [NSString stringWithFormat:@"%@ es raiz, con error %f",xMedio,error];
            
            UIAlertView * alert = [[UIAlertView alloc] initWithTitle:@"Raiz encontrada" message:[NSString stringWithFormat:@"%@",xmErrorString] delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            
            [alert show];
            NSLog(@"%f es raiz, con error %e",xm,error);
            NSLog(@"%@ es raiz, con error %f",xMedio,error);
           // [resultados setResultados:xm :error];
            
        } else if (cont == iter) {
            NSLog(@"No se hallo raiz con %d iteraciones\n", iter);
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:[NSString stringWithFormat:@"No se hallo raiz con %D iteraciones",iter] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [alert show];
            //[resultados setResultado:iter];
        } else {
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:[NSString stringWithFormat:@"Error"] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            [alert show];
        }
        
    }
}

@end
