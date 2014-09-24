//
//  secantTableViewController.h
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "secant.h"

@interface secantTableViewController : UITableViewController{
    
    UITextField *func;
    UITextField* x0;
    UITextField* x1;
    UITextField* tol;
    UITextField* ite;
}

@end
