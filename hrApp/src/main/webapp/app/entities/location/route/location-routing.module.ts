import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { LocationComponent } from '../list/location.component';
import { LocationDetailComponent } from '../detail/location-detail.component';
import { LocationUpdateComponent } from '../update/location-update.component';
import { LocationRoutingResolveService } from './location-routing-resolve.service';

const locationRoute: Routes = [
  {
    path: '',
    component: LocationComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LocationDetailComponent,
    resolve: {
      location: LocationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LocationUpdateComponent,
    resolve: {
      location: LocationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LocationUpdateComponent,
    resolve: {
      location: LocationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(locationRoute)],
  exports: [RouterModule],
})
export class LocationRoutingModule {}
