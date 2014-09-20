//
//  bisectionTableViewController.m
//  Methods
//
//  Created by Daniel Klinkert on 9/19/14.
//  Copyright (c) 2014 _danielKlinkert_. All rights reserved.
//

#import "bisectionTableViewController.h"

@interface bisectionTableViewController ()

@end

@implementation bisectionTableViewController

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
    return 2;
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
    
    // Configure the cell...
    CGRect field = CGRectMake(10, 10, tableView.frame.size.height, 25);
    
    if (!function) {
        function = [[UITextField alloc]initWithFrame:field];
    }
    
    if (!xi) {
        xi = [[UITextField alloc]initWithFrame:field];
    }
    
    if (!xs) {
        xs = [[UITextField alloc]initWithFrame:field];
    }
    
    if (!tolerancia) {
        tolerancia = [[UITextField alloc]initWithFrame:field];
    }
    
    if (!nIteraciones) {
        nIteraciones = [[UITextField alloc]initWithFrame:field];
    }
    
    if (indexPath.section == 0) {
        switch (indexPath.row) {
                
            case 0:
                [cell addSubview:function];
                [function setPlaceholder:@"Función"];
                break;
            case 1:
                [cell addSubview:xi];
                [xi setPlaceholder:@"Valor Inferior"];
                break;
            case 2:
                [cell addSubview:xs];
                [xs setPlaceholder:@"Valor superior"];
                break;
            case 3:
                [cell addSubview:tolerancia];
                [tolerancia setPlaceholder:@"Tolerancia"];
                break ;
            case 4:
                [cell addSubview:nIteraciones];
                [nIteraciones setPlaceholder:@"Numero de Iteraciones"];
                break;
                
            default:
                break;
        }
    }else if (indexPath.section == 1){
        
        [cell.textLabel setText:@"Calcular"];
        [cell.textLabel setTextAlignment:NSTextAlignmentCenter];
    }
    
    
    return cell;
}



-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.section == 1) {
        if ([function.text isEqualToString:@""] || [xi.text isEqualToString:@""] || [xs.text isEqualToString:@""]|| [nIteraciones.text isEqualToString:@""]) {
            UIAlertView * all = [[UIAlertView alloc]initWithTitle:@"Error" message:@"Debes completar todos los campos para poder calcular el resultado" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
            
            [all show];
        }else{
            
            
            id myMethod;
            myMethod = [bisection new];
            
            [myMethod initWithFunction:function.text valorInicial:xi.text valorSuperior:xs.text tolerancia:[tolerancia.text doubleValue] iteraciones:[nIteraciones.text intValue] ];
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
