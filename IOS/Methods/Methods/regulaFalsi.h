//
//  regulaFalsi.h
//  Methods
//
//  Created by Daniel Klinkert on 9/20/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <Foundation/Foundation.h>
#include "evaluador.h"

@interface regulaFalsi : NSObject{
    
    double xi;
    double xs;
    NSString* func;
    double tol;
    int iter;
}


- (void)initWithFunction:(NSString *)funcion valorInferior:(NSString *)xInf valorSuperior:(NSString *)xSup tolerancia:(double)toler iteraciones:(int)ite;


- (void)regulaFalsi;

@end
