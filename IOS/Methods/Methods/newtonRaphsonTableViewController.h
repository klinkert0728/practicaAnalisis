//
//  newtonRaphsonTableViewController.h
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NewtonRaphson.h"

@interface newtonRaphsonTableViewController : UITableViewController{
    UITextField* function;
    UITextField* functionDerivate;
    UITextField* x0;
    UITextField* tolerancia;
    UITextField* iteraciones;
}

@end
