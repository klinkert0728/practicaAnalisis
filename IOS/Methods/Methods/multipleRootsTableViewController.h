//
//  multipleRootsTableViewController.h
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "multipleRoots.h"

@interface multipleRootsTableViewController : UITableViewController{
    
    UITextField *function;
    UITextField *firstDerivateFunction;
    UITextField *secondDerivateFunction;
    UITextField *inicialValue;
    UITextField *tolerance;
    UITextField *numberOfIterations;
}

@end
