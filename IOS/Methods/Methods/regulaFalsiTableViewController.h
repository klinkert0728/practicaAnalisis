//
//  regulaFalsiTableViewController.h
//  Methods
//
//  Created by Daniel Klinkert on 9/20/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "regulaFalsi.h"

@interface regulaFalsiTableViewController : UITableViewController{
    
    UITextField * function;
    UITextField * xi;
    UITextField * xs;
    UITextField * tolerancia;
    UITextField * nIteraciones;
}

@end
