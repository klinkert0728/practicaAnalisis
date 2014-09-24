//
//  multipleRoots.h
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#include "evaluador.h"

@interface multipleRoots : NSObject{
    
    NSString *funcion;
    NSString *dFuncion;
    NSString *ddFuncion;
    double x1;
    double tol;
    int iter;
}

- (void)initWithFunction:(NSString*)funct functionFirstDerivate:(NSString*)dFunc functionSecondDerivate:(NSString*)ddFunc valorInicialMultiplesRaices:(double)xRaicMult tolerancia:(double)tolRaicMult iteraciones:(int)iterRaicMult;

- (void)multipleRoots;

@end
