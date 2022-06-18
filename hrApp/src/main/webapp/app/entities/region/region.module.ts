import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RegionComponent } from './list/region.component';
import { RegionDetailComponent } from './detail/region-detail.component';
import { RegionUpdateComponent } from './update/region-update.component';
import { RegionDeleteDialogComponent } from './delete/region-delete-dialog.component';
import { RegionRoutingModule } from './route/region-routing.module';

@NgModule({
  imports: [SharedModule, RegionRoutingModule],
  declarations: [RegionComponent, RegionDetailComponent, RegionUpdateComponent, RegionDeleteDialogComponent],
  entryComponents: [RegionDeleteDialogComponent],
})
export class RegionModule {}
