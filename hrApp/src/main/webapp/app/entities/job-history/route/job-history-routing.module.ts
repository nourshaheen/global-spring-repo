import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { JobHistoryComponent } from '../list/job-history.component';
import { JobHistoryDetailComponent } from '../detail/job-history-detail.component';
import { JobHistoryUpdateComponent } from '../update/job-history-update.component';
import { JobHistoryRoutingResolveService } from './job-history-routing-resolve.service';

const jobHistoryRoute: Routes = [
  {
    path: '',
    component: JobHistoryComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: JobHistoryDetailComponent,
    resolve: {
      jobHistory: JobHistoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: JobHistoryUpdateComponent,
    resolve: {
      jobHistory: JobHistoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: JobHistoryUpdateComponent,
    resolve: {
      jobHistory: JobHistoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(jobHistoryRoute)],
  exports: [RouterModule],
})
export class JobHistoryRoutingModule {}
