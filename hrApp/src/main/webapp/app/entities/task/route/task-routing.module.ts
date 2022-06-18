import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TaskComponent } from '../list/task.component';
import { TaskDetailComponent } from '../detail/task-detail.component';
import { TaskUpdateComponent } from '../update/task-update.component';
import { TaskRoutingResolveService } from './task-routing-resolve.service';

const taskRoute: Routes = [
  {
    path: '',
    component: TaskComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TaskDetailComponent,
    resolve: {
      task: TaskRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TaskUpdateComponent,
    resolve: {
      task: TaskRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TaskUpdateComponent,
    resolve: {
      task: TaskRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(taskRoute)],
  exports: [RouterModule],
})
export class TaskRoutingModule {}
