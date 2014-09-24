//
//  NewtonRaphson.h
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <Foundation/Foundation.h>
#include "evaluador.h"
#import <UIKit/UIKit.h>

@interface NewtonRaphson : NSObject{
    
    NSString *funcion;
    NSString *dFuncion;
    double X0;
    double tol;
    int iter;
}


- (void)initWithFunction:(NSString *)func functionDerivate:(NSString*)dFunc valorInicial:(double)x0 tolerancia:(double)tolNew iteraciones:(int) iterNew;

- (void)NewtonRaphson;

@end
