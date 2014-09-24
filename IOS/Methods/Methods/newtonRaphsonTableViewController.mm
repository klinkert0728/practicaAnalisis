//
//  newtonRaphsonTableViewController.m
//  Methods
//
//  Created by Daniel Klinkert on 9/23/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "newtonRaphsonTableViewController.h"

@interface newtonRaphsonTableViewController ()

@end

@implementation newtonRaphsonTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 2 ;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    // Return the number of rows in the section.
    if (section == 0 ) {
        return 5;
    }else{
        return 1;
    }
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
    
    
    CGRect field = CGRectMake(10, 10, tableView.frame.size.height, 25);
    if (!function) {
        function = [[UITextField alloc] initWithFrame:field];
        [function setPlaceholder:@"Función F(x)"];
    }
    if (!functionDerivate) {
        functionDerivate= [[UITextField alloc] initWithFrame:field];
        [functionDerivate setPlaceholder:@"F'(x)"];
    }
    
    if (!tolerancia) {
        tolerancia = [[UITextField alloc] initWithFrame:field];
        [tolerancia setPlaceholder:@"Tolerancia"];
    }
    if (!x0) {
        x0 = [[UITextField alloc] initWithFrame:field];
        [x0 setPlaceholder:@"Valor Inicial"];
    }
    if (!iteraciones) {
        iteraciones= [[UITextField alloc] initWithFrame:field];
        [iteraciones setPlaceholder:@"Iteraciones"];
    }
    
    if (indexPath.section == 0) {
        switch (indexPath.row) {
            case 0:
                [cell addSubview:function];
                break;
            case 1:
                [cell addSubview:functionDerivate];
                break;
            case 2:
                [cell addSubview:tolerancia];
                break;
            case 3:
                [cell addSubview:x0];
                break;
            case 4:
                [cell addSubview:iteraciones];
                break;
                
            default:
                break;
        }
    }else if (indexPath.section ==1){
        
        if (indexPath.row == 0) {
            [cell.textLabel setText:@"Calcular"];
            [cell.textLabel setTextAlignment:NSTextAlignmentCenter];
        }
    }
    // Configure the cell...
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 1) {
        if ([function.text isEqualToString:@""] || [functionDerivate.text isEqualToString:@""] || [tolerancia.text isEqualToString:@""]|| [x0.text isEqualToString:@""] || [iteraciones.text isEqualToString:@""]) {
            
            UIAlertView * all = [[UIAlertView alloc]initWithTitle:@"Error" message:@"Debes completar todos los campos para poder calcular el resultado" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            
            [all show];
        }else{
            
            
            id myMethod;
           myMethod = [NewtonRaphson new];
            
            [myMethod initWithFunction:function.text functionDerivate:functionDerivate.text valorInicial:[x0.text doubleValue] tolerancia:[tolerancia.text doubleValue] iteraciones:[iteraciones.text intValue]];
        
        }
    }
}
/*
 // Override to support conditional editing of the table view.
 - (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
 // Return NO if you do not want the specified item to be editable.
 return YES;
 }
 */

/*
 // Override to support editing the table view.
 - (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
 if (editingStyle == UITableViewCellEditingStyleDelete) {
 // Delete the row from the data source
 [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
 } else if (editingStyle == UITableViewCellEditingStyleInsert) {
 // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
 }
 }
 */

/*
 // Override to support rearranging the table view.
 - (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath {
 }
 */

/*
 // Override to support conditional rearranging of the table view.
 - (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
 // Return NO if you do not want the item to be re-orderable.
 return YES;
 }
 */

/*
 #pragma mark - Navigation
 
 // In a storyboard-based application, you will often want to do a little preparation before navigation
 - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
 // Get the new view controller using [segue destinationViewController].
 // Pass the selected object to the new view controller.
 }
 */

@end
