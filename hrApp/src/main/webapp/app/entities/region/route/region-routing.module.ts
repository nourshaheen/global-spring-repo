import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RegionComponent } from '../list/region.component';
import { RegionDetailComponent } from '../detail/region-detail.component';
import { RegionUpdateComponent } from '../update/region-update.component';
import { RegionRoutingResolveService } from './region-routing-resolve.service';

const regionRoute: Routes = [
  {
    path: '',
    component: RegionComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RegionDetailComponent,
    resolve: {
      region: RegionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RegionUpdateComponent,
    resolve: {
      region: RegionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RegionUpdateComponent,
    resolve: {
      region: RegionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(regionRoute)],
  exports: [RouterModule],
})
export class RegionRoutingModule {}
