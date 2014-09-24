//
//  secant.h
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#include "evaluador.h"

@interface secant : NSObject{
    
    NSString *func;
    double x0;
    double x1;
    double tol;
    int ite;
}

- (void)initWithFunction:(NSString*)function valorIniciar:(double)x0Sec valorSgte:(double)x1Sec tolerancia:(double)toler iteraciones:(int) iter;

- (void)secante;

@end
