//
//  bisection.h
//  Methods
//
//  Created by Daniel Klinkert on 9/19/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#include "evaluador.h"

@interface bisection : NSObject {
    
    NSString* xinf;
    NSString* xsup;
    NSString* func;
    double tol;
    int iter;
}

- (void)initWithFunction:(NSString *)funcion valorInicial:(NSString *)xInf valorSuperior:(NSString *)xSup tolerancia:(double)toler iteraciones:(int)ite;


-(void)biseccion;


@end
