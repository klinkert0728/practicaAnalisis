//
//  setPoint.h
//  Methods
//
//  Created by Daniel Klinkert on 9/20/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <Foundation/Foundation.h>
#include "evaluador.h"
#import <UIKit/UIKit.h>

@interface setPoint : NSObject{
    
    double x0;
    double tol;
    int iter;
    NSString * function;
    NSString * functionG;
}

- (void)initWithFunction:(NSString *)funct functionG:(NSString *)funcG x0:(double)valorInicial iter:(int)ite tol:(double)toler;

- (void)setPoint;

@end
