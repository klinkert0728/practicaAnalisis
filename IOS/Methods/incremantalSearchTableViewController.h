//
//  incremantalSearchTableViewController.h
//  Methods
//
//  Created by Daniel Klinkert on 9/11/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <math.h>
#import "busquedaIncremental.h"

@interface incremantalSearchTableViewController : UITableViewController{
    
    UITextField * funcion;
    UITextField * valorInicial;
    UITextField * delta;
    UITextField * numeroIteraciones;
}

@end
