//
//  busquedaIncremental.h
//  Methods
//
//  Created by Daniel Klinkert on 9/13/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#include "evaluador.h"

@interface busquedaIncremental : NSObject{
    
    NSString *func;
	NSString *X0;
	NSString *X1;
	double delt;
	int iter;
}

- (void)initMethod:(NSString *)funcion
             delta:(NSString *)delta
          xInicial:(NSString *)xInicial
       iteraciones:(NSString *)iteraciones;

- (void )busquedaIncremental;
@end
