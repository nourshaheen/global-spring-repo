import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DepartmentComponent } from '../list/department.component';
import { DepartmentDetailComponent } from '../detail/department-detail.component';
import { DepartmentUpdateComponent } from '../update/department-update.component';
import { DepartmentRoutingResolveService } from './department-routing-resolve.service';

const departmentRoute: Routes = [
  {
    path: '',
    component: DepartmentComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DepartmentDetailComponent,
    resolve: {
      department: DepartmentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DepartmentUpdateComponent,
    resolve: {
      department: DepartmentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DepartmentUpdateComponent,
    resolve: {
      department: DepartmentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(departmentRoute)],
  exports: [RouterModule],
})
export class DepartmentRoutingModule {}
