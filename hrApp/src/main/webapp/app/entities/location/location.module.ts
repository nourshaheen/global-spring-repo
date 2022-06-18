import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { LocationComponent } from './list/location.component';
import { LocationDetailComponent } from './detail/location-detail.component';
import { LocationUpdateComponent } from './update/location-update.component';
import { LocationDeleteDialogComponent } from './delete/location-delete-dialog.component';
import { LocationRoutingModule } from './route/location-routing.module';

@NgModule({
  imports: [SharedModule, LocationRoutingModule],
  declarations: [LocationComponent, LocationDetailComponent, LocationUpdateComponent, LocationDeleteDialogComponent],
  entryComponents: [LocationDeleteDialogComponent],
})
export class LocationModule {}
